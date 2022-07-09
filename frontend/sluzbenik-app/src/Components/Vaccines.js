import Header from "./Header";
import AuthService from "../Services/AuthService";

function Vaccines() {
  return (
    <div>
      <Header user={AuthService.getUser()}></Header>
    </div>
  );
}
export default Vaccines;
