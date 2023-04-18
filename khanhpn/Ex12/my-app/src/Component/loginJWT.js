import axios from 'axios';
const proxy="http://localhost:8082";
const prefix="/api";
const after="/login";
export const callLoginApi= async (user) => {
    const payload = await axios.post(proxy + prefix + after, user)
    return payload;
} 