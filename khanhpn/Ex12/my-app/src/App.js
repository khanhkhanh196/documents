import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from 'react-bootstrap/Button';
// import logo from './logo.svg';
import './App.css';
import Greeting from "./Component/Greeting";
import { render } from "react-dom";
import { Component } from "react";
import Login from './Component/Login';
import { AuthContext } from '../src/Component/AuthProvider';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
function App (){
  const existingTokens = localStorage.getItem("tokens");
  const [authTokens, setAuthTokens] = useState(existingTokens);
  
  const setTokens = (data) => {
    localStorage.setItem("tokens", data);
    setAuthTokens(data);
  }

    return(
      <AuthContext.Provider value={{ authTokens, setAuthTokens: setTokens}}>
        <BrowserRouter>
        <div>
          <Login />
        </div>
      </BrowserRouter>
    </AuthContext.Provider>
    );
}

export default App;