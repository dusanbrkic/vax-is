import { Stack } from "@mui/material";
import AuthService from "../Services/AuthService";
import Header from "./Header";

function Interest() {
  return (
    <div>
      <Header user={AuthService.getUser()}></Header>
      <Stack></Stack>
    </div>
  );
}
export default Interest;
