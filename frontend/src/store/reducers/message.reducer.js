const initialState = {
    isConnect: false,
    users: ['123'],
    usersActive: [],
};

const messageReducer = (state = initialState, action) => {
    const { type, payload } = action;
    var temp;
    switch (type) {
        case "CONNECT":
            return {
                ...state,
                isConnect: payload,
            };
        case "CHAT_FRIENDS":
            temp = new Map(state.usersActive);
            payload.forEach(member => {
                temp.set(member.id, [])
            });
            return {
                ...state,
                usersActive: [...temp]
            }
        case "GET_MESSAGE":
            temp = new Map(state.usersActive)
            if (temp.has(payload.memberID)) {
                temp.set(payload.memberID, [...temp.get(payload.memberID), ...payload.messages])
            }
            return {
                ...state,
                usersActive: [...temp]
            };
        case "USER_ACTIVE":
            return {
                ...state,
                usersActive: payload
            }

        case "SEND_MESSAGE":
            return {
                ...state,
            };
        case "RECEIVED_MESSAGE":
            temp = new Map(state.usersActive)
            if (temp.has(payload.memberID)) {
                temp.set(payload.memberID, [...temp.get(payload.memberID), payload.messages])
            }
            return {
                ...state,
                usersActive: [...temp]
            }
        default:
            return state;
    }
};
export default messageReducer;