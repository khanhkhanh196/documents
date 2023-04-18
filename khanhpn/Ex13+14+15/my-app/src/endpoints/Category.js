
import axios from 'axios';

const proxy="http://localhost:8082";
const prefix="/admin";
const after="/category";
const pageable="/categories";

export const axios_config = (token) => {
    axios.interceptors.request.use(function (config) {
        config.headers.Authorization =  `Bearer ${token}`;
    
        return config;
    });
}

export  const getCategory = async (page, pageSize) =>{
    const payload = await axios.get(proxy + prefix + pageable + `?page=${page}&size=${pageSize}`);
    console.log(proxy + prefix + pageable + `?page=${page}&size=${pageSize}`)
    return payload.data;
}

export const getCategoryByID = async (id) => {
    const payload = await axios.get(proxy + prefix + after + "/"+ id);
    return payload.data;
}
export const deleteCategoryByID = async (id) => {
    const payload = await axios.delete(proxy + prefix + after + "/"+ id);
    return payload.data;
}

export const addNewCategory = async (category) => {
    return await axios.post(proxy + prefix + after, category);
}

export const updateCategory = async (id,category) => {
    return await axios.put(proxy + prefix + after + "/" + id, category);
}
