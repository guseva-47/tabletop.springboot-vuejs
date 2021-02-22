<template>
  <div class="table">
    <section>
      <div class="container-xxl row">
        <!-- Контейнер не основной но важной информации (узкий слева) -->
        <div 
          v-if="table.id !== '-1'"
          class="pad order-lg-2 col-lg-3 order-1">
          <edit-person-block 
            :table="this.table"
            :subscribers="this.subscribers"
            :notSubscribers="this.notSubscribers"
            :subscribe="this.subscribe"
            :unsubscribe="this.unsubscribe"
          />
        </div>
        <!-- Контейнер для основного блока на странице, при большом экране он располгается справа, при маленьком опускается ниже другого контейнера -->
        <div class="pad col-xxl-8 offset-xxl-1 col-lg-9 offset-lg-0 order-lg-1 order-2">
        
          <!-- Изображение иллюстрация для статьи -->
          <div class="">
            <img
              class="img-fluid rounded mx-auto d-block"
              src="http://placehold.it/1400x300"
              alt=""
              style="max-height: 250px"
            />
          </div>

          <form class="review-form" @submit.prevent="saveTable">
            <!-- Редактируемый заголовок статьи -->
            <h3>
              <input 
                id="title" 
                v-model="table.title" 
                placeholder="Название игры"
                class="text-center h3 w-100 form-control-plaintext"
              >
            </h3>

            <!-- Редактируемый текст статьи -->
            <p>
              <textarea 
                id="description"
                class="pad w-100 form-control-plaintext"
                placeholder="Описание игры"
                v-model="table.description" />
            </p>
            
            <div class="row mb-3 pad">
              <label for="system" class="col-sm-3 col-form-label">
                Игровая система:
              </label>
              <div class="col-sm-8">
                <select 
                  id="system" 
                  class="form-select" 
                  v-model="table.gameSystem"
                >
                  <option v-for="item in this.gameSystems" :key="item.value">
                      {{ item.title }}
                    </option>
                </select>
              </div>
            </div>

            <!-- Кнопки сохранения, отмены и удаления записи -->
          <div class="d-grid gap-2 d-sm-flex justify-content-end">
            <button class="btn btn-outline-danger" @click="deleteTable() , $router.push({ name: 'hello'});">Удалить запись</button>
            <!-- Отмена должна возвращать исходное значение, а не отправлять на гл страницу -->
            <button class="btn btn-outline-primary" @click="$router.push({ name: 'hello'})">Отмена</button>
            <button class="btn btn-outline-primary" type="submit" value="Submit">Сохранить</button>
          </div>

          </form>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import api from './backend-api'
import EditPersonBlock from './EditPersonBlock.vue';

export default {
  components: {
    EditPersonBlock
  },
  data() {
    return {
      table: {
        id: '',
        title: '',
        description: '',
        gameSystem: 'Honey heist',
        owner: ''
      },
      gameSystems: [],
      subscribers: [],
      notSubscribers: [],
    }
  },
  async created() {
    if (this.$route.params.id !== '-1') {
      this.getTable(this.$route.params.id)
        .then(() =>{
          this.getSubscribers();
          this.getNotSubscribers();
      })
    }
    else {
      this.table = {
        id: '-1',
        title: 'Новое название',
        description: 'Описание игры',
        gameSystem: 'Honey heist',
        owner: this.$store.state.user
      }
    }
    
    // TODO надо получать с сервера, по хорошему
    this.gameSystems = [
      {
        value: 'HONEYHEIST',
        title: 'Honey heist'
      },
      {
        value: 'CRASHPANDAS',
        title: 'Crash pandas'
      }
    ]
  },
  
  methods: {
    async getTable(id) {
      await api
        .getTable(id)
        .then((result) => {
          this.table = result.data;
          this.table.gameSystem = this.gameSystemTitle(this.table.gameSystem);
        })
        .catch((e) => {
          this.errors.push(e);
        });
    },

    async getSubscribers() {
      await api
      .getSubscribers(this.table.id)
      .then(responce => {
        if (responce.status === 200)
          this.subscribers = responce.data;
      })
      .catch((e) => {
          this.errors.push(e);
      });
    },

    async getNotSubscribers() {
      await api
      .getNotSubscribers(this.table.id)
      .then(responce => {
        if (responce.status === 200)
          this.notSubscribers = responce.data;
      })
      .catch((e) => {
          this.errors.push(e);
      });
    },

    async getGameSystems() {
      await api.getGameSystems()
        .then((result) => {
          this.gameSystems = result.data;
        })
        .catch((e) => {
        this.errors.push(e);
      });
    },

    async deleteTable() {
      if(this.table.id === '-1') return;
      await api.deleteTable(this.table.id);
    },
    
    async saveTable() {

      let newTabletop = {
        title: this.table.title,
        description: this.table.description,
        gameSystem: this.gameSystemValue(this.table.gameSystem),
        owner: this.table.owner
      }
      if (this.table.id === '-1'){
        await api.postTable(newTabletop).then(responce => {
          
          if (responce.status === 200){ 
            this.table.id = responce.data.id;
            this.getSubscribers();
            this.getSubscribers();
            this.getNotSubscribers();
          }
        })
      }
      else {
        await api.putTable(this.table.id, newTabletop);
      }
    },

    async subscribe(newSubscribers) {
      await api
        .subscribe(this.table.id, newSubscribers)
        .then(responce => {
          if (responce.status === 200) {
            this.subscribers = responce.data.subscribers;
            this.notSubscribers = responce.data.notsubscribers;
          }
      })
      .catch((e) => {
          this.errors.push(e);
      });
    },

    async unsubscribe(users) {
      await api
        .unSubscribe(this.table.id, users)
        .then(responce => {
          if (responce.status === 200) {
            this.subscribers = responce.data.subscribers;
            this.notSubscribers = responce.data.notsubscribers;
          }
      })
      .catch((e) => {
          this.errors.push(e);
      });
    },

    gameSystemValue(gsTitle) {
    let fullName = this.gameSystems.find(element => element.title === gsTitle );
    return fullName.value;
    },
    gameSystemTitle(gsValue) {
    let fullName = this.gameSystems.find(element => element.value === gsValue );
    return fullName.title;
    }
  },
  
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style >
.pad {
  padding-top: 10px;
  padding-bottom: 10px;
}
</style>
