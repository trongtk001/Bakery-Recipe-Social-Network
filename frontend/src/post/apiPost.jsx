import axios from "axios";

export const create = (userId, token, post) => {
  return fetch(`${process.env.REACT_APP_API_URL}/post/new/${userId}`, {
    method: "POST",
    headers: {
      Accept: "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: post,
  })
    .then((response) => {
      return response.json();
    })
    .catch((err) => console.log(err));
};

// export const list = () => {
//     return fetch(`${process.env.REACT_APP_API_URL}/posts`, {
//         method: "GET"
//     })
//         .then(response => {
//             return response.json();
//         })
//         .catch(err => console.log(err));
// };

// with pagination
export const list = (page) => {
  return fetch(`${process.env.REACT_APP_API_URL}/post?page=${page}&size=5`, {
    method: "GET",
  })
    .then((response) => {
      console.log(response.json());
      return response.json();
    })
    .catch((err) => console.log(err));
};
export const listAll = async () => {
  return await (await axios.get(`${process.env.REACT_APP_API_URL}/post?page=1&size=30`)).data.list;
};

export const singlePost = (postId) => {
  return fetch(`${process.env.REACT_APP_API_URL}/post/${postId}`, {
    method: "GET",
  })
    .then((response) => {
      return response.json();
    })
    .catch((err) => console.log(err));
};

export const listByUser = (userId, token) => {
  return fetch(`${process.env.REACT_APP_API_URL}/post/by/${userId}`, {
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
  })
    .then((response) => {
      return response.json();
    })
    .catch((err) => console.log(err));
};

export const remove = (postId, token) => {
  return fetch(`${process.env.REACT_APP_API_URL}/post/${postId}`, {
    method: "DELETE",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
  })
    .then((response) => {
      return response.json();
    })
    .catch((err) => console.log(err));
};

export const update = (postId, token, post) => {
  console.log(postId, token, post);
  return fetch(`${process.env.REACT_APP_API_URL}/post/${postId}`, {
    method: "PUT",
    headers: {
      Accept: "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: post,
  })
    .then((response) => {
      return response.json();
    })
    .catch((err) => console.log(err));
};

export const likePost = (userId, token, postId) => {
  return fetch(`${process.env.REACT_APP_API_URL}/post/like`, {
    method: "PUT",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({ userId, postId }),
  })
    .then((response) => {
      return response.json();
    })
    .catch((err) => console.log(err));
};

export const unlike = (userId, token, postId) => {
  return fetch(`${process.env.REACT_APP_API_URL}/post/unlike`, {
    method: "PUT",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({ userId, postId }),
  })
    .then((response) => {
      return response.json();
    })
    .catch((err) => console.log(err));
};

export const comment = (userId, token, postId, comment) => {
  return fetch(`${process.env.REACT_APP_API_URL}/post/comment`, {
    method: "PUT",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({ userId, postId, comment }),
  })
    .then((response) => {
      return response.json();
    })
    .catch((err) => console.log(err));
};

export const uncomment = (userId, token, postId, comment) => {
  return fetch(`${process.env.REACT_APP_API_URL}/post/uncomment`, {
    method: "PUT",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({ userId, postId, comment }),
  })
    .then((response) => {
      return response.json();
    })
    .catch((err) => console.log(err));
};
