import React, { useState, useContext, useEffect } from "react";
import AuthService from "../../../services/AuthService";
import UserContext from "../UserContext";
import AppContext from "../AppContext";

const UserProvider: React.FC = (props) => {
  const appCtx = useContext(AppContext);
  const { handleOpenNotifier, levels } = appCtx;
  const [token, setToken] = useState(AuthService.getUser());
  const [userInfo, setUserInfo] = useState();

  const handleSignIn = async (username: string, password: string) => {
    const response = await AuthService.login(username, password);
    if (!response) {
      handleOpenNotifier(true, "Lỗi hệ thống", levels.warn);
    }
    if (response && response.status === 200) {
      handleOpenNotifier(true, "Đăng nhập thành công", levels.success);
      setToken(AuthService.getUser());
    }
    if (response && response.status === 401) {
      handleOpenNotifier(true, "username/password không đúng", levels.warn);
    }
    return response;
  };

  const handleSignOut = () => {
    AuthService.logout();
    setToken(undefined);
  };

  useEffect(() => {
    const response = AuthService.getUserInfo();
    if (!response) {
      return;
    }
    response.then((result) => {
      if (result && result.status === 200) {
        setUserInfo(result.data);
      } else {
        AuthService.removeUserSession();
        setToken(undefined);
      }
    });
  }, []);

  return (
    <UserContext.Provider
      value={{
        token: token,
        userInfo: userInfo,
        handleSignIn: handleSignIn,
        handleSignOut: handleSignOut,
      }}
    >
      {props.children}
    </UserContext.Provider>
  );
}

export default UserProvider;