import React, { Component } from "react";
import UserGreeting from './UserGreeting';
import Login from './Login';
export default function Greeting(props) {
    const isLoggedIn = props.isLoggedIn;
    const userName = props.username;
    if (isLoggedIn == true) {
        return <UserGreeting username={userName}/>;
  
    }
    return <Login />;
  }