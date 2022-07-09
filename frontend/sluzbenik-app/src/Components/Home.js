import AuthService from "../Services/AuthService";
import Header from "./Header";

function Home() {
  return (
    <div>
      <Header user={AuthService.getUser()}></Header>
      <img src="/vakcina.png"></img>
    </div>
  );
}
export default Home;
