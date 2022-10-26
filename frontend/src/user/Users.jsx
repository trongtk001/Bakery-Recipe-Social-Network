import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import useQuery from "../hooks/useQuery";
import Load from "../components/load";
import axios from "axios";

function Users() {
  const query = useQuery();
  const text = query.get("text");
  const [data, setData] = useState({
    list: [],
  });
  const [loading, setLoading] = useState(false);
  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      fetch(`${process.env.REACT_APP_API_URL}/member?q=${text}&page=1&size=5`, {
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
  }, [text]);

  useEffect( async() => {

    let data = await axios.get(`${process.env.REACT_APP_API_URL}/member?q=&page=1&size=20`)
    if(data){
      console.log(data.data)
    }
      
    });


  return (
    <div className="container pro-container m-auto">
      <h2 className="mt-5 mb-5">Users : {text}</h2>
      <div className="my-6 grid lg:grid-cols-3 grid-cols-2 gap-1.5">
        {!loading ? (
          data.list && data.list.length !== 0 ? (
            <>
              {data.list.map((user) => (
                <div>
                  <div className="bg-red-500 max-w-full lg:h-64 h-40 rounded-md relative overflow-hidden uk-transition-toggle">
                    <img
                      className="w-full h-full absolute object-cover inset-0"
                      src={`${user.avatar}`}
                      onError={(i) =>
                        (i.target.src = `https://source.unsplash.com/random/?bakery,bake,${user.name}`)
                      }
                      alt={user.name}
                    />
                  </div>
                  <div className="card-body">
                    <h5 className="card-title">{user.name}</h5>
                    <p className="card-text" style={{ lineHeight: "40px" }}>
                      {user.email}
                    </p>
                    <Link
                      to={`/user/${user.id}`}
                      className="bg-pink-500 font-bold hover:bg-pink-600 hover:text-white px-6 py-3  rounded text-sm text-white to-pink-600"
                    >
                      View Profile
                    </Link>
                  </div>
                </div>
              ))}
            </>
          ) : (
            "NOT FOUND"
          )
        ) : (
          <div>
            <Load isSmall={true} />
          </div>
        )}
      </div>
    </div>
  );
}

export default Users;
