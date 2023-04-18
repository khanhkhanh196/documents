import React, {Component} from 'react';
import CategoryList from './CategoryComponent/CategoryList';
import CategoryDetail from './CategoryComponent/CategoryDetail';
import {getCategoryByID} from '../endpoints/Category';
import Table from 'react-bootstrap/Table';
import './css/categorydetail.css';
import {Button} from '@material-ui/core';
import { Redirect} from "react-router-dom";
import { withRouter } from "react-router";

class Dashboard extends Component {
    state = {
        categoryDetail :[],
        theId:'',
        islogout: false,
     }

     signOut = () => {
        // localStorage.removeItem("token");
        this.setState({
          islogout: true
        });
      };



    render(){
        if (this.state.islogout) {
            return <Redirect to="/login" />;
        }
        else if (this.state.isShowDetail) {
            return <Redirect to={{ 
                pathname: "/detail", 
                state: {category: this.state.categoryDetail}
                }} />;
        } else
        return(
            <div className="container">
                <div className="dashboard"><h3>Dash Board</h3></div>
                <div className="greeting"><h2>Hello </h2></div>

                <Button onClick={this.signOut} >
                Sign Out
                </Button>
                <div className="container">
                    <CategoryList name={this.props.username} /> 
                </div>
            </div>
        );
    }
}
export default withRouter(Dashboard);