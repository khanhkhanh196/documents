import React, {Component} from 'react';
import { render } from 'react-dom';

export default class UserGreeting extends Component{
    render(){
        return(
            <div>
                <div className="usergreeting"><p>{this.props.username}</p></div>
            </div>
            
        );
    }
}