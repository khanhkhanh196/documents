
import React, {Component, useContext } from "react";
import { Route, Redirect, RouteProps } from "react-router-dom";
import {AuthContext} from '../context/auth';



const PrivateRoute: React.ComponentType<RouteProps> = ({ path: Path, component: Component, ...rest }) => {

    const {isLoggin} = useContext(AuthContext);

    if(!Component) return null;

    return (
        <Route
            {...rest}
            render = { (props) => {
                if(isLoggin === true) {
                        return <Component {...props}/>
    
                } else if(isLoggin === false) {
                    return <Redirect             
                    to={{ pathname: "/login", state: { prevLocation: Path } }} />
                }
            }
                
            }
        />
    );
}

export default PrivateRoute;