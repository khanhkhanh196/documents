import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from 'react-bootstrap/Button';
// import logo from './logo.svg';
import './App.css';
import Greeting from "./Component/Greeting";
import { render } from "react-dom";
import { Component } from "react";
import Login from './Component/Login';

 class App extends Component{
   render(){
    return(
      <Login />
    );
   }
}

export default App;