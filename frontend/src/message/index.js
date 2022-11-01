
import { useEffect, useState } from "react";
import FormChat from "./FormChat";
import FrChat from "./FrChat";
import Sidebar from "./Sidebar";
import SockJS from 'sockjs-client';
import { over } from 'stompjs';
import { useIsLogin } from "../hooks/useIsLogin";
import axios from "axios";
import { useDispatch, useSelector } from "react-redux";
import { setListMess, setListMessNULL } from "../store/actions/user.action";

var stompClient;

export default function Message() {
    const { listMess } = useSelector((state) => state.user);
    const dispatch = useDispatch()
    const { isLogin } = useIsLogin();
    const [username, setUsername] = useState(isLogin.username);
    const [message, setMessage] = useState('');
    const [users, setUsers] = useState([]);
    const [active, setActive] = useState({});
    const [usersActive, setUsersActive] = useState([]);
    // const [listMess, setListMess] = useState([{senderID: 10,receiverID: null,messageBody: 'Em an com chua'},{senderID: 1,receiverID: null,messageBody: 'Shut up'}]);


    var isConnect = false;
    const header = {
        "Authorization": `Bearer ${isLogin.token}`
    };


    useEffect(async () => {
        axios({
            method: "GET",
            url: `${process.env.REACT_APP_API_URL}/follow/followers/${isLogin.id}?page=1&size=100`,
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${isLogin.token}`,
            },
            data: null,
        })
        .then((res) => {
            setUsers(res.data.list);
        })
        .catch((err) => {
            console.log(err);
        });

        connect();
        console.log("connected...");

        

    }, []);

    useEffect(() => {
        if (users[0]) {
            setActive({
                username: users[0].username,
                name: users[0].name
            })
        }
    }, [users])

    useEffect(() => {
        dispatch(setListMessNULL())
    }, [active]);

    const connect = () => {
        if (!isConnect) {
            let Sock = new SockJS('http://35.198.245.224/api/ws');
            stompClient = over(Sock);
            // stompClient.debug = null;
            stompClient.connect(header, onConnected, onError);
        }
    }


    const onConnected = () => {
        console.log("connected");
        isConnect = true
        stompClient.subscribe('/app/online', onActive, header);
        stompClient.subscribe(`/user/${username}/queue/private`, onMessageReceived, header);
        stompClient.subscribe(`/user/${username}/info/error`, onMessageReceived, header);
    }

    const chat = () => {

        if (message) {
            stompClient.send("/app/private-message", header, JSON.stringify({
                senderID: isLogin.id,
                receiverID: active.id,
                messageBody: message
            }));
            dispatch(setListMess({
                senderID: isLogin.id,
                receiverID: active.id,
                messageBody: message
            }))
        }        
    }

    const onMessageReceived = (payload) => {
        // setListMess(payload.body);
        let a = JSON.parse(payload.body)
        dispatch(setListMess({
            senderID: a.senderID,
            receiverID: a.receiverID,
            messageBody: a.messageBody
        }))
    }

    const onActive = (payload) => {
        setUsersActive(JSON.parse(payload.body));
    }

    console.log(listMess)
    const onError = (err) => {
        console.log(err);

    }
    return (
        <>
            <div class="container m-auto pt-5">
                <div class="lg:flex lg:shadow lg:bg-white lg:space-y-0 space-y-8 rounded-md lg:-mx-0 -mx-5 overflow-hidden lg:dark:bg-gray-800 h-full" >

                    <Sidebar setUsers={setUsers} active={active} setActive={setActive} users={users} ></Sidebar>
                    <div class="lg:w-8/12 bg-white dark:bg-gray-800">
                        <FrChat active={active} usersActive={usersActive} ></FrChat>
                        <FormChat message={message} chat={chat} setMessage={setMessage} listMess={listMess}></FormChat>
                    </div>
                </div>
            </div>
        </>
    );
}
