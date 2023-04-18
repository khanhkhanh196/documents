import React, {useContext } from "react";
import { Route, Redirect } from "react-router-dom";
import {AuthContext} from '../context/auth';
function PrivateRoute({ path: Path, component: Component, ...rest }) {

    const {isLoggin} = useContext(AuthContext);

    return (
        <Route
            {...rest}
            render={props => {
                if(isLoggin === true) {
                    console.log("logged in")
                    return <Component {...props} />
                    
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