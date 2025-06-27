<template>
    <div>
        <String
            label="Email"
            v-model="value.email"
            :editMode="editMode"
        />
        <String
            label="AuthorName"
            v-model="value.authorName"
            :editMode="editMode"
        />
        <String
            label="Introduction"
            v-model="value.introduction"
            :editMode="editMode"
        />
        <String
            label="FeaturedWorks"
            v-model="value.featuredWorks"
            :editMode="editMode"
        />
        <Boolean
            label="IsApprove"
            v-model="value.isApprove"
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
    name: 'Author',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "authors",
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
