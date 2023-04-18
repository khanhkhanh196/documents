import React from 'react';
import axios from 'axios';
const proxy="http://localhost:8082";
const prefix="/admin";
const after="/category";

export  const getCategory = async () =>{
    const payload = await axios.get(proxy + prefix + after);
    return payload.data;
}

export const getCategoryByID = async (id) => {
    const payload = await axios.get(proxy + prefix + after + "/"+ id);
    return payload.data;
}