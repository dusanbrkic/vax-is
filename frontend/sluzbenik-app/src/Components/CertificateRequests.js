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

function CertificateRequests() {
  const [requests, setRequests] = useState([]);

  const navigation = useNavigate();
  const handleAcceptRequest = () => {};

  const handleDeclineRequest = () => {};

  useEffect(() => {
    axios
      .get(Environment.baseURL + "sluzbenik/api/zahtev-sertifikata/all", {
        headers: {
          "Content-Type": "application/xml;charset=utf-8",
          "Access-Control-Allow-Origin": "*",
        },
      })
      .then((response) => {
        console.log(response);
      });
  }, []);
  return (
    <div>
      <Header user={AuthService.getUser()}></Header>
      <Stack>
        <TableContainer>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Name</TableCell>
                <TableCell>Surname</TableCell>
                <TableCell>Requst date</TableCell>
                <TableCell>View documents</TableCell>
                <TableCell>Accept</TableCell>
                <TableCell>Deny</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {requests.map((r) => (
                <TableRow>
                  <TableCell>{r.name}</TableCell>
                  <TableCell>{r.surname}</TableCell>
                  <TableCell>{r.date}</TableCell>
                  <TableCell>
                    <Button
                      onClick={() => {
                        navigation("/citizenDocuments/" + r.jmbg);
                      }}
                      variant="contained"
                    >
                      View documents
                    </Button>
                  </TableCell>
                  <TableCell>
                    <Button
                      onClick={() => {
                        handleAcceptRequest();
                      }}
                      variant="contained"
                      color="success"
                    >
                      Accept
                    </Button>
                  </TableCell>
                  <TableCell>
                    <Button
                      onClick={() => {
                        handleDeclineRequest();
                      }}
                      variant="contained"
                      color="error"
                    >
                      Decline
                    </Button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Stack>
    </div>
  );
}
export default CertificateRequests;
