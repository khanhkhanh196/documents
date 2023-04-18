import React, { useContext } from "react";
import { Route, Redirect, RouteProps } from "react-router-dom";

// handle the public routes
const PublicRoute: React.FunctionComponent<RouteProps> = ({ component: Component, ...rest }) => {
  // const userCtx = useContext(UserContext);
  if (!Component) return null;
  return (
    <Route
      {...rest}
      render={(props) =>
        // !userCtx.user ? (
          <Component {...props} />
        // ) : (
            // <Redirect to={{ pathname: "/" }} />
          // )
      }
    />
  );
};

export default PublicRoute;
