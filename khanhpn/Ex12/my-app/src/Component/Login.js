import React from "react";
import Form from "react-bootstrap/Form";
import Button from 'react-bootstrap/Button';
import './css/Login/Login.css';
import Dashboard from "./dashboard";
import 'bootstrap/dist/css/bootstrap.min.css';
import {callLoginApi} from './loginJWT';
import {useState} from 'react';
import { useAuth } from './AuthProvider';
const Login = (props) => {


    const [isLoggedIn,setLogin] = useState(false);
    const [username,setUsername] = useState('');
    const [password,setPass] = useState('');
    const [todos,setTodo] = useState([]);
    const [user,setUser] = useState({});
    const {setToken} = useAuth();;

    const validateForm = () => {
        return username.length > 0 && password.length > 0;
    }

     const Login = async () => {
      const user = {
        username: username,
        password: password
      }
      const reponse = await callLoginApi(user)
      if(reponse != null && reponse.status === 200){
        setToken(reponse.data.accessToken);
        setLogin(true);
      }
      else alert("Đăng nhập thất bại!")
      // console.log("login payload  " + payload.data)
    }

    const handleSubmit = (event) =>{
        event.preventDefault();
        
        
        Login();
        // if(this.state.username === accounts.username && this.state.password === accounts.password ) {
        //     this.setState({isLoggedIn: true});
        // } else {
        //     this.setState({isLoggedIn: false});
        //     alert('Wrong username or password')
        // }
    }
  
    if(isLoggedIn) {
        return (
            <div className="container">
                <Dashboard/>
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
              value={this.username}
              onChange = {(newValue) => this.setState({username:newValue.target.value})}
            />
          </Form.Group>
          <Form.Group size="lg" controlId="password">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              value={this.password}
              onChange = {(newValue) => this.setState({password:newValue.target.value})}
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