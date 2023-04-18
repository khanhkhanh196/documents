import React from "react";
import Form from "react-bootstrap/Form";
import Button from 'react-bootstrap/Button';
import './css/Login/Login.css';
import UserGreeting from "./UserGreeting";
import 'bootstrap/dist/css/bootstrap.min.css';

var accounts = {
    username:'khanh',
    password:'123'
  }
  
export default class Login extends React.Component {

    constructor(props) {
        super(props);
        this.validateForm = this.validateForm.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.state = {
            isLoggedIn: false,
            username:'',
            password:'',
            
        };
    }
    validateForm() {
        return this.state.username.length > 0 && this.state.password.length > 0;
    }

    handleSubmit(event) {
        event.preventDefault();
        if(this.state.username === accounts.username && this.state.password === accounts.password ) {
            this.setState({isLoggedIn: true});
        } else {
            this.setState({isLoggedIn: false});
			alert('Wrong username or password');
        }
    }
  
    render() {
    if(this.state.isLoggedIn) {
        return (
            <div className="container">
                <div className="dashboard"><h3>Dash Board</h3></div>
                <UserGreeting username={accounts.username}/>
            </div>
        
        );
    } else {
      return (
        <div className="Login">
        <Form onSubmit={this.handleSubmit}>
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
          <Button block size="lg" type="submit" disabled={!this.validateForm()}>
            Login
          </Button>
        </Form>
      </div>
      );
    }

    }
}
  
