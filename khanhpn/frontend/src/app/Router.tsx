import React from 'react';
import { Switch } from "react-router-dom";
import PublicRoute from "./utils/PublicRoute";
import PrivateRoute from "./utils/PrivateRoute";
import LoginPage from "./pages/LoginPage";
import DashboardPage from "./pages/DashboardPage";
import DomainsPage from "./modules/Demo/DomainsPage";
import DomainFormPage from './modules/Demo/DomainFormPage';
import ProductCategory from '../Product/ProductCategory'

const Router: React.FC = () => {
    return (
        <Switch>
            <PublicRoute exact path="/login" component={() => <LoginPage />} />

            <PublicRoute exact path="/categories" component={() => <ProductCategory />} />

            <PrivateRoute exact path="/" component={DashboardPage} />
            <PrivateRoute exact path="/demo" component={DomainsPage} />
            <PrivateRoute exact path="/demo/create" component={DomainFormPage} />
            <PrivateRoute exact path="/demo/:id" component={DomainFormPage} />
        </Switch >
    );
};

export default Router;