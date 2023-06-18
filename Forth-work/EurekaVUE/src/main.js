import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import  'element-plus/dist/index.css'
import axios from 'axios'
axios.defaults.baseURL = '/api'


axios.defaults.headers.common['token'] = sessionStorage.getItem("token");
axios.interceptors.request.use(
  config => {
    if (localStorage.getItem("token")) {
      config.headers.common['token'] = sessionStorage.getItem("token");    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);
createApp(App).use(store).use(router).use(ElementPlus).mount('#app')
