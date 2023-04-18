import React, { useContext } from "react";
import { Route, Redirect, RouteProps } from "react-router-dom";
import CustomLayout from "../layouts/CustomLayout";

const PrivateRoute: React.FunctionComponent<RouteProps> = ({ component: Component, ...rest }) => {
  // const userCtx = useContext(UserContext);
  if (!Component) return null;
  return (
    <Route
      {...rest}
      render={props => (
        // userCtx.user ?
          <CustomLayout {...props}><Component {...props} /></CustomLayout>
          // :
          // <Redirect
            // to={{ pathname: "/login", state: { from: props.location } }}
          // />
      )
      }
    />
  );
}

export default PrivateRoute;
