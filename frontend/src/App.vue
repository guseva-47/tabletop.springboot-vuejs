<template>
  <div id="app">
    <div id="nav" class="">
      <div class="container-xxl d-flex flex-row justify-content-between">
        <div class=""> 
          <router-link to="/">Tabletop</router-link>
        </div>
        <div 
          class=""
          v-if="this.$store.state.user == null"
          >
          <a @click="login">Войти</a>
        </div>
        <div v-else>
          <a @click="logout">Выйти</a>
        </div>
      </div>
    </div>
    <router-view />
  </div>
</template>

<script>

export default {
  name: 'app',
  methods: {
    login() {
      this.$store.dispatch('authorize');
    },
    logout() {
      this.$store.dispatch('unauthorize');
    }
  }
}
</script>

<style lang="scss">

#nav {
  padding-left: 30px;
  padding: 10px;
  background-color: #5f9ea0;
  a {
    font-weight: bold;
    color: #ffffff;
    text-decoration: none;
    &.router-link-exact-active {
     color: #ffffff;
     text-decoration: none
    }
  }
}
</style>
