import React, {useState} from "react";
import { createContext } from 'react';
import {checkJWtafterLogin} from "../endpoints/LoginJWT";
import {axios_config} from '../endpoints/Category';
export const AuthContext = createContext();

export default function AuthProvider(props) {
  const existingTokens = localStorage.getItem("accessToken");
    const authTokens = useState(existingTokens);
    const [isLoggin,setIsLoggin] = useState();

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