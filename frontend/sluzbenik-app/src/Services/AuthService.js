import Environment from "../Constants/Environment";

const testUser = { name: "Hajduk", surname: "Dusan" };

const getUser = () => {
  const user = JSON.parse(sessionStorage.getItem("user"));
  return user;
};

const isAuth = () => {
  const user = JSON.parse(sessionStorage.getItem("user"));
  return user === null;
};

const setUser = (user) => {
  sessionStorage.setItem("user", JSON.stringify(user));
};

const removeUser = () => {
  sessionStorage.removeItem("user");
};
export default { getUser, setUser, removeUser, isAuth };
