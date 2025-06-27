import { createRouter, createWebHashHistory } from 'vue-router';

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      component: () => import('../components/pages/Index.vue'),
    },
    {
      path: '/authors',
      component: () => import('../components/ui/AuthorGrid.vue'),
    },
    {
      path: '/users',
      component: () => import('../components/ui/UserGrid.vue'),
    },
    {
      path: '/readings',
      component: () => import('../components/ui/ReadingGrid.vue'),
    },
    {
      path: '/manuscripts',
      component: () => import('../components/ui/ManuscriptGrid.vue'),
    },
    {
      path: '/points',
      component: () => import('../components/ui/PointGrid.vue'),
    },
    {
      path: '/books',
      component: () => import('../components/ui/BookGrid.vue'),
    },
    {
      path: '/ais',
      component: () => import('../components/ui/AiGrid.vue'),
    },
    {
      path: '/bookpages',
      component: () => import('../components/BookpageView.vue'),
    },
    {
      path: '/readingPages',
      component: () => import('../components/ReadingPageView.vue'),
    },
  ],
})

export default router;
