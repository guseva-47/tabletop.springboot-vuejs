<template>
  <div class="row pad">
    <div class="col-md-4">
      <router-link :to="{ name: 'table', params: { id: table.id } }">
        <img
          class="img-fluid rounded mb-3 mb-md-0"
          src="http://placehold.it/700x300"
          alt=""
        />
      </router-link>
    </div>
    <div class="col-md-8 position-relative" >

      <h3><router-link :to="{ name: 'table', params: { id: table.id } }" id="link">
        {{ table.title }}
      </router-link></h3>
      <p><router-link :to="{ name: 'table', params: { id: table.id } }" id="link">
        {{ table.description }}
      </router-link></p>
      <div
        v-if="isOwner" 
      >
        <button class="btn btn-outline-primary" @click="$router.push({ name: 'editTable', params: { id: table.id } })">
          Редактировать
        </button>
        <div class="btn btn-outline-danger" type="button" value="X" @click="del()">
          Удалить запись
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["table", "deleteTable", "tables", "isOwner"],
  methods: {
    del() {
      this.deleteTable(this.table);
    },
  },
};
</script>

<style lang="scss">
.pad {
  padding-top: 10px;
  padding-bottom: 10px;
}
#link {
  color: #000000;
  text-decoration: none;
  &.router-link-exact-active {
    color: #000000;
    text-decoration: none;
  }
}
</style>