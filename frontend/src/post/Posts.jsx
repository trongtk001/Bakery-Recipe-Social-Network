import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { getListPost } from "../store/actions/post.action";
import Load from "../components/load";
import DataPost from "./DataPost";
import { useHistory } from "react-router-dom";
function Posts() {
  const history=useHistory();
  const dispatch = useDispatch();
  const [totalPages, setTotalPages] = useState(0);
  const [page, setPage] = useState(1);
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(false);
  useEffect(() => {
    dispatch(getListPost(page, posts, setPosts, setTotalPages, setLoading,history));
    // eslint-disable-next-line
  }, [page]);

  return posts.length === 0 ? (
    <h5 className="px-4">not found</h5>
  ) : (
    <div className="space-y-5 flex-shrink-0 lg:w-7/12">
      {posts.map((post, i) => {
        return <DataPost post={post} key={i} />;
      })}
      {loading ? (
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
        <>
          {totalPages !== page && (
            <button
              onClick={() => setPage(page + 1, setLoading(true))}
              className="bg-gradient-to-bl font-semibold from-pink-400 px-6 py-3 rounded-full text-sm text-white to-pink-600"
              style={{ display: "flex", margin: "25px auto" }}
            >
              <span className="w_ia">Load More..</span>
            </button>
          )}
        </>
      )}
    </div>
  );
}

export default Posts;
