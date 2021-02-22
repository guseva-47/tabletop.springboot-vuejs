<template>
  <div class="hello">
    <section>
      <div class="container-xxl">
        <div class="row pad" style="padding: 12px">
          <button
            v-if="this.$store.state.user" 
            class="btn btn-outline-success btn-lg" 
            @click="$router.push({ name: 'editTable', params: { id: '-1' } })">
            + Добавить запись
            </button>
        </div>

        <table-row v-for="table in tables"
                      :key="table.id"
                      :table="table"
                      :isOwner="isOwner(table)"
                      :deleteTable="deleteTable"
                      :tables="tables" />
      </div>
    </section>
  </div>
</template>

<script>
import TableRow from './TableRow';

export default {
  components: {
    TableRow,
  },
  data() {
    return {
      tables: [],
    };
  },
  created: async function() {
    await this.getTables();
    this.tables = this.$store.state.tables;
    this.user = this.$store.state.user;
    
  },
  methods: {
    async getTables() {
      await this.$store.dispatch('getTables');
    },
    async deleteTable(table) {
      await this.$store.dispatch('deleteTable', table);
      this.tables = this.$store.state.tables;
    },
    isOwner(table) {
      if(this.$store.state.user && table){
        return this.$store.state.user.id == table.owner.id;
      }
      return false;
    }
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.pad {
  padding-top: 10px;
  padding-bottom: 10px;
}
</style>
