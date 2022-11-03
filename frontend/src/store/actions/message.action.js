import callApi from "../../utils/api/callApi"

export const getMessage = (memberID, friendID, page, size, setMessage) => {
    const userLogin = localStorage.getItem("userLogin");
    const token = userLogin ? JSON.parse(userLogin).token : "";
    return (dispatch) => {
        callApi(`message?page=${page}&size=${size}`, 'POST', {body:{memberID, friendID}}, token).then((res) => {
            setMessage(res.data.list);
            dispatch({type: "GET_MESSAGE", payload: {memberID: friendID, messages: res.data.list}})
        }).catch(error => console.log(error));
    }
}

export const messageSelector = state => new Map(state.message.usersActive);