import {
  Stack,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Button,
} from "@mui/material";
import { useEffect, useState } from "react";
import Header from "./Header";
import { useNavigate } from "react-router-dom";
import Environment from "../Constants/Environment";
import axios from "axios";
import AuthService from "../Services/AuthService";

function CertificateRequest() {
  return (
    <div>
      <Header user={AuthService.getUser()}></Header>
      <Stack></Stack>
    </div>
  );
}
export default CertificateRequest;
