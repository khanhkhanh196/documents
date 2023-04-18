import React from "react";
// import logo from './logo.svg';
import './App.css';
import Login from './Component/Login';
import CategoryAddNew from './Component/CategoryComponent/CategoryAddNew';
import CategoryDetail from './Component/CategoryComponent/CategoryDetail';
import AdminPage from './Component/AdminPage';
import AuthProvider from '../src/context/auth';
import {
  BrowserRouter,
  Switch,
  Route,
} from "react-router-dom";
import PrivateRoute from './Component/PrivateRoute';



 function App (){
    
    return(
      
        <BrowserRouter>
          <Switch>
            <Route exact path="/" component={Login} />
            <Route path="/login" component={Login} />
            <AuthProvider>
              <Switch>
                <PrivateRoute  path="/admin" component={AdminPage}/>
                <PrivateRoute path="/category" component={CategoryAddNew}/>
                <PrivateRoute path="/detail" component={CategoryDetail}/>
              </Switch>
              </AuthProvider>
          </Switch>
        </BrowserRouter>
       
    );
}

export default App;