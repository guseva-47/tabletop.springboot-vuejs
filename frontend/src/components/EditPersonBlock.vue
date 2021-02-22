<template>
<!-- Контейнер не основной но важной информации (узкий слева) -->
<div>
    <!-- Кнопка запуска игры -->
    <a class="btn btn-success btn-lg w-100" href="#" id="add-button">▷ Начать игру</a>
    <!-- Блок с информацией об организаторе игры -->
    <div class="person-block">
        <div class="col">
            <p class="p0"> {{table.owner.name}}</p>
            <p class="small">{{table.owner.email}}</p>
        </div>
    </div>
      

    <!-- Блок со списком остальных участников игры -->
    <div class="person-block">
        <a class="btn btn w-100" href="#" id="add-button" data-bs-toggle="modal" data-bs-target="#addPlayersModal">+ Пригласить игрока</a>
        <hr>
        <div class="row">
          <div 
            class="col" 
            v-for="person in subscribers" 
            :key="person.id"
          >
            <img class="icon" src="http://placehold.it/100x100" alt="">
            <div class="">
                <p class="p0">{{person.name}}</p>
                <p class="small">{{person.email}}</p>
            </div>
          </div>
        </div>
        <hr style="margin-bottom: 0; margin-top: 5px;">
        <a class="btn btn w-100" href="#" id="add-button" data-bs-toggle="modal" data-bs-target="#deletePlayersModal">- Удалить игрока</a>
    </div>

    <!-- Modal пиглашение (добавление) игроков -->
    <div class="modal fade" id="addPlayersModal" tabindex="-1" aria-labelledby="addPlayersLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Пригласить игроков</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
              <div 
                class="form-check"
                v-for="person in notSubscribers"
                :key="person.id"
              >
                <div class="form-check">
                  <input 
                    class="form-check-input" 
                    type="checkbox"
                    :id="person.id"
                    :value="person"
                    v-model="toSubscribe"
                    >
                  <label class="form-check-label" :for="person.id">
                    {{person.name}} {{person.email}}
                  </label>
                </div>

              </div>
            </div>
            
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                <button type="button" class="btn btn-primary"  @click="toInvite()">Пригласить</button>
            </div>
        </div>
        </div>
    </div>

    <!-- Modal удаление игроков -->
    <div class="modal fade" id="deletePlayersModal" tabindex="-1" aria-labelledby="exampleModalLabel2" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Удалить игроков</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <div 
                class="form-check"
                v-for="person in subscribers"
                :key="person.id"
              >
                <div class="form-check">
                  <input 
                    class="form-check-input" 
                    type="checkbox"
                    :value="person" 
                    :id="person.id"
                    v-model="toUnSubscribe"
                    >
                  <label class="form-check-label" :for="person.id">
                    {{person.name}} {{person.email}}
                  </label>
                </div>
              </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                <button type="button" class="btn btn-primary" @click="toOut()">Удалить</button>
            </div>
        </div>
        </div>
    </div>

</div>
  
</template>

<script>

export default {
  props: ["table", "subscribers", "notSubscribers", "subscribe", "unsubscribe"],
  data() {
    return {
      toSubscribe: [],
      toUnSubscribe: []
    }
  },
  async created() {

    
  },
  
  methods: {

    toInvite() {
      this.subscribe(this.toSubscribe).then( () => this.toSubscribe = []);
    },

    toOut() {
      this.unsubscribe(this.toUnSubscribe).then( () => this.toUnSubscribe = []);
    }
  }
  
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
.pad {
  padding-top: 10px;
  padding-bottom: 10px;
}

.icon {
  width: 60px; 
  height: 60px;
  background: #cccccc;
  border-radius: 50%;
  margin: 5px;
}

.person-block {
  margin-top: 10px;
  padding: 8px;
  margin-left: 0;
  margin-right: 0;
  border-radius: 5px;
  background-color: #cfe2e2;
}

.p0 {
  padding: 0;
  margin: 0;
}
</style>

