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
    <div class="person-block" >
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
    </div>

</div>
  
</template>

<script>

export default {
  props: ["table", "subscribers"],

};
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
  padding: 5px;
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

