import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { getListPost } from "../store/actions/post.action";
import Load from "../components/load";
import DataPost from "./DataPost";
import { useHistory } from "react-router-dom";
function Posts() {
    const history = useHistory();
    const dispatch = useDispatch();
    const [page, setPage] = useState(1);
    const [posts, setPosts] = useState([]);
    const [isNotthing, setIsNothing] = useState(false);
    useEffect(() => {
        dispatch(getListPost(page, posts, setPosts, setIsNothing, history));
        // eslint-disable-next-line
    }, [page]);

    const loadMore = () => {
        window.onscroll = function () {
            let h;
            if (document.querySelector(".load-more")) {
                h = document.querySelector(".load-more").clientHeight;
            }

            let a = h - this.pageYOffset;
            if (a <= 1000 && !isNotthing) {
                setPage(page + 1);
            }
        };
    };

    loadMore();

    return posts.length === 0 ? (
        <h5 className="px-4">not found</h5>
    ) : (
        <div className="space-y-5 flex-shrink-0 lg:w-7/12 load-more">
            {posts.map((post, i) => {
                return <DataPost post={post} key={i} />;
            })}
            {!isNotthing ? (
                <button
                    disabled
                    className="bg-gradient-to-bl font-semibold from-pink-400 px-6 py-3 rounded-full text-sm text-white to-pink-600"
                    style={{
                        display: "flex",
                        margin: "25px auto",
                        height: "45px",
                        alignItems: "center",
                    }}
                >
                    <span className="w_ia">Load More</span>
                    <Load isSmall={true} />
                </button>
            ) : (
                <p className='text-pink-600 text-center'>Not thing to show</p>
            )}
        </div>
    );
}

export default Posts;
