<template>
    <div>
        <Number
            label="AuthorId"
            v-model="value.authorId"
            :editMode="editMode"
        />
        <String
            label="BookName"
            v-model="value.bookName"
            :editMode="editMode"
        />
        <String
            label="Category"
            v-model="value.category"
            :editMode="editMode"
        />
        <String
            label="IsBestSeller"
            v-model="value.isBestSeller"
            :editMode="editMode"
        />
        <String
            label="AuthorName"
            v-model="value.authorName"
            :editMode="editMode"
        />
        <String
            label="AiImage"
            v-model="value.aiImage"
            :editMode="editMode"
        />
        <String
            label="AiSummary"
            v-model="value.aiSummary"
            :editMode="editMode"
        />
        <String
            label="BookContent"
            v-model="value.bookContent"
            :editMode="editMode"
        />
        <Number
            label="View"
            v-model="value.view"
            :editMode="editMode"
        />
        <Date
            label="Date"
            v-model="value.date"
            :editMode="editMode"
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
    name: 'Book',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "books",
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
