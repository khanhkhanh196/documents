import React, {useState} from "react";
import { createContext } from 'react';
import {checkJWtafterLogin} from "../endpoints/LoginJWT";
import {axios_config} from '../endpoints/Category';


interface Props {
  children:React.ReactNode;
}
const myInitalState: any = {
  authTokens: '',
  isLoggin: null
}
export const AuthContext = createContext(myInitalState);

const AuthProvider  = (props:Props) => {
    const existingTokens: string = localStorage.getItem("accessToken") as string;
    const authTokens = useState<string>(existingTokens);
    const [isLoggin,setIsLoggin] = useState<boolean>();

    const validateUser =  async () => {
      axios_config(authTokens[0]);
      const response = await checkJWtafterLogin();
      if(response.status === 200) {
        setIsLoggin(true);
        return true
      }
      else{
        setIsLoggin(false);
        return false;
      } 
  } 

    validateUser();

    return (
  
      <AuthContext.Provider 
        value={{authTokens,isLoggin}}>
        {props.children}
      </AuthContext.Provider>
        
      );

}
export default AuthProvider;