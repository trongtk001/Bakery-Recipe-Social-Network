import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getUser } from "../store/actions/user.action";
import defaltAvatar from "../images/avatar.png";

export function useIsLogin() {
    const { user } = useSelector((state) => state.user);
    const { loading } = useSelector((state) => state.common);

    const dispatch = useDispatch();
    useEffect(
        () => {
            user && dispatch(getUser(user.id, true));
        },
        // eslint-disable-next-line
        []
    );
    const avatar = !user || !user?.avatar ? defaltAvatar : user.avatar;

    return {
        isLogin: user,
        avatar,
        loading,
    };
}
