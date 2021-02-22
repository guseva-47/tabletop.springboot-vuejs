import Vue from 'vue'
import Vuex from 'vuex'
import api from '../components/backend-api'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    count: 0,
    user: null,
    tables: [],
  },
  getters: {
    getUser(state) {
      return state.user;
    },
  },
  mutations: {
    increment (state) {
      state.count++
    },
    setUser(state, user) {
      state.user = user;
    },
    setTables(state, tables) {
      state.tables = tables;
    },
    deleteTable(state, table) {
      let t = state.tables;
      t.splice(t.indexOf(table), 1);
      state.tables = t;
    }
  },
  actions: {
    async authorize(context) {
      // await api
      //   .login()
      //   .then((result) => {
      //     let tables = result.data;
      //     commit('setTables', tables);
      //   })
      //   .catch((e) => {
      //     this.errors.push(e);
      //   });
      let user = {
        "id": "1",
        "email": null,
        "last_active_at": null,
        "name": null
      };
      context.commit('setUser', user);
    },
    async unauthorize(context) {
      context.commit('setUser', null);
    },

    async getTables({ commit }) {
      await api
        .getTables()
        .then((result) => {
  
          
          let tables = result.data;
          commit('setTables', tables);
        })
        .catch((e) => {
          this.errors.push(e);
        });
    },

    async deleteTable({ commit }, table) {
      let tableId = table.id;
      await api
        .deleteTable(tableId)
        .then((result) => {
          if (result.status === 200) {
            commit('deleteTable', table);
          }
        });
    }
    
  }
})