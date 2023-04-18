import React from "react";

import Form from "react-bootstrap/Form";
import Button from 'react-bootstrap/Button';
import './css/Login.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {useState} from 'react';
import {callLoginApi} from '../endpoints/LoginJWT';
import {Redirect} from 'react-router-dom';

const Login = (props) => {


  const [isLoggedIn,setLogin] = useState(false);
  const [username,setUsername] = useState('');
  const [password,setPass] = useState('');

  const validateForm = () => {
      return username.length > 0 && password.length > 0;
  }

   const Login = async () => {
    const user = {
      username: username,
      password: password
    }
    const reponse = await callLoginApi(user).catch(error => console.log(error))
    if(reponse != null && reponse.status === 200){
      localStorage.setItem("accessToken",reponse.data.accessToken);
      setLogin(true);
    }
    else alert("Login Failed");
  }

  const handleSubmit = (event) =>{
      event.preventDefault();
      Login();
  }

  if(isLoggedIn) {
      return (
          <div>
              <Redirect to="/admin"/>
          </div>
      
      );
  } else {
    return (
      <div className="Login">
      <Form onSubmit={handleSubmit}>
        <Form.Group size="lg" controlId="email">
          <Form.Label>UserName</Form.Label>
          <Form.Control
            autoFocus
            type="username"
            value={username}
            onChange = {(newValue) => setUsername(newValue.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="password">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            value={password}
            onChange = {(newValue) => setPass(newValue.target.value)}
          />
        </Form.Group>
        <Button block size="lg" type="submit" disabled={!validateForm()}>
          Login
        </Button>
      </Form>
    </div>
    );
  }

}

export default Login;