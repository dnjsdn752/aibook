<template>
    <div>
        <Boolean
            label="IsReading"
            v-model="value.isReading"
            :editMode="editMode"
        />
        <Date
            label="StartReading"
            v-model="value.startReading"
            :editMode="editMode"
        />
        <String
            label="WebUrl"
            v-model="value.webUrl"
            :editMode="editMode"
        />
        <UserId
            offline
            label="구독자 ID"
            v-model="value.userId"
            :editMode="editMode"
            @change="change"
        />
        <BookId
            offline
            label="도서 ID"
            v-model="value.bookId"
            :editMode="editMode"
            @change="change"
        />
        <v-row class="ma-0 pa-0">
            <v-spacer></v-spacer>
            <v-btn width="64px" color="primary" @click="save">
                저장
            </v-btn>
        </v-row>
    </div>
</template>


<script>
import BaseEntity from './base-ui/BaseEntity.vue'

export default {
    name: 'Reading',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "readings",
        value: {
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
