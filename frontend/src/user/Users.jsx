import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import useQuery from "../hooks/useQuery";
import Load from "../components/load";

function Users() {
  const query = useQuery();
  const text = query.get("text") || "";
  const [data, setData] = useState({
    list: [],
  });
  const [data2, setData2] = useState({
    list: [],
  });
  const [loading, setLoading] = useState(false);
  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      fetch(`${process.env.REACT_APP_API_URL}/member?q=${text}&page=1&size=4`, {
        method: "GET",
      })
        .then((response) => response.json())
        .then((result) => {
          setData((data) => ({
            ...data,
            list: result.list,
          }));
          setLoading(false);
        })
        .catch((err) => {
          console.log(err);
          setLoading(false);
        });
    };
    fetchData();
    const fetchData2 = async () => {
      setLoading(true);
      fetch(`${process.env.REACT_APP_API_URL}/post?q=${text}&page=1&size=6`, {
        method: "GET",
      })
        .then((response) => response.json())
        .then((result) => {
          setData2((data) => ({
            ...data,
            list: result.list,
          }));
          setLoading(false);
        })
        .catch((err) => {
          console.log(err);
          setLoading(false);
        });
    };
    fetchData2();
  }, [text]);



  return (
    <div className="mx-auto max-w-[1440px] px-10 pro-container m-auto">
      <h2 className=" mb-5">Users {text}</h2>
      <div className="my-6 grid lg:grid-cols-2 grid-cols-2 gap-4">
        {!loading ? (
          data.list && data.list.length !== 0 ? (

            data.list?.map((item, index) => {
              return  <Link to={`/user/${item.id}`} key={index} className="bg-white duration-400 overflow-hidden border rounded-[16px] hover:shadow-md transition">
                        <div className="flex gap-4 p-4  border-r-4 hover:border-pink-600 ">
                          <img className="w-14 h-14 rounded-full " src={item.avatar ? item.avatar : 'https://images.squarespace-cdn.com/content/v1/54b7b93ce4b0a3e130d5d232/1519987020970-8IQ7F6Z61LLBCX85A65S/icon.png?format=1000w'} alt="" />
                          <div>
                            <p className="text-[#000]">{item.name}</p>
                            <p>@{item.username} - {item.email}</p>
                          </div>
                        </div>
                        <div className="flex px-4 pb-4 gap-4 font-bold">
                          {/* <div className="border rounded-md p-2 hover:opacity-90 cursor-pointer text-white bg-pink-600">Follow</div> */}
                          <div className="border rounded-md p-2 hover:opacity-90 cursor-pointer text-pink-600 border-pink-600">View profile</div>
                        </div>
                    </Link>})
          ) : ("NOT FOUND")) : (
          <div>
            <Load isSmall={true} />
          </div>
        )}
      </div>
      <h2 className="mt-5 mb-5">Posts: {text}</h2>
      <div className="my-6 grid lg:grid-cols-3 grid-cols-2 gap-6">
        {!loading ? (
          data2.list && data2.list.length !== 0 ? (

            data2.list?.map((item, index) => {
              return  <Link to={`/post/${item.id}`} key={index} className="bg-white border rounded-[16px] overflow-hidden hover:shadow-md">
                        <div className="w-full">
                          <img className="w-full h-[10rem] object-cover " src={item.recipe.steps.length > 0 ? item.recipe.steps[0].image : `https://source.unsplash.com/random/?bakery,bake`} alt="" />
                        </div>
                        <div className="p-4">
                          <ul className=" mb-2 flex gap-2 ">
                              {item.recipe.tool && (item.recipe.tool?.map((_item, index) => {
                                if(index <=2 ){
                                  return <li key={index} className=" px-2 py-1 text-pink-600 ">#{_item}</li>
                                }else{
                                  return ''
                                }
                              }))}
                          </ul>
                            <hr />
                            <p className=" text-lg line-clmap-4 mt-2 h-[112px] ">{item.postBody}</p>
                              <p className="mt-3">{item.likes.length} Likes - {new Date(item.createDate).toDateString()}</p>
                          </div>
                    </Link>})
          ) : ("NOT FOUND")) : (
          <div>
            <Load isSmall={true} />
          </div>
        )}
      </div>
    </div>
  );
}

export default Users;
