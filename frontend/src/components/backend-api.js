import axios from 'axios'

const AXIOS = axios.create({
  baseURL: `/table`,
  timeout: 1000
});


export default {
  getTables() {
    return AXIOS.get(`/`);
  },
  getTable(tableId) {
    return AXIOS.get(`/` + tableId);
  },
  deleteTable(tableId) {
    return AXIOS.delete(`/` + tableId);
  },
  putTable(tableId, table) {
    return AXIOS.put(`/`+ tableId, table);
  },
  postTable(newTable) {
    return AXIOS.post('/', newTable);
  },
  getGameSystems() {
    return AXIOS.get('/getAllSystems');
  },

  getSubscribers(tableId) {
    return AXIOS.get('/' + tableId + '/sub');
  },
  getNotSubscribers(tableId) {
    return AXIOS.get('/' + tableId + '/unsub');
  },
  // отправить пользователей, которых нужно подписать на игру
  subscribe(tableid, newSubscribers) {
    console.log(newSubscribers);
    return AXIOS.put('/' + tableid + '/sub', newSubscribers);
  },

  unSubscribe(tableid, subscribers) {
    console.log('unsub');
    console.log(subscribers);
    return AXIOS.put('/' + tableid + '/unsub', subscribers);
  },

}


