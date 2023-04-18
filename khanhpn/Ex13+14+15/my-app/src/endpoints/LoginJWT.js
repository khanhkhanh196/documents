import axios from 'axios';
const proxy="http://localhost:8082";
const prefix="/api";
const after="/login";
const protect = "/protected";
export const callLoginApi= async (user) => {
    const payload = await axios.post(proxy + prefix + after, user)
    return payload;
} 

export const checkJWtafterLogin = async () => {
    const check = await axios.post(proxy + prefix + protect)
    return check;
}