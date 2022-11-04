import React, { useEffect } from 'react';
import styled from 'styled-components';
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';

import Pagination from 'react-bootstrap/Pagination';
import Button from 'react-bootstrap/Button';
import Stack from 'react-bootstrap/Stack';

import ActionTable from '../components/tables/ActionTable';
import CustomePagination from '../components/common/CustomPagination';
import { postActions } from '../features/posts/postSlice';
import axiosClient from '../api/axiosClient';

let active = 2;
let items = [];
for (let number = 1; number <= 5; number++) {
  items.push(
    <Pagination.Item key={number} active={number === active}>
      {number}
    </Pagination.Item>
  );
}

const PostPageStyled = styled.div`
  h1 {
    margin-bottom: 12px;
  }
`;

const PostPage = () => {
  const tableHeaders = ['ID', 'Title', 'Like', 'Author', 'Created At'];
  const isLoading = useSelector((state) => state.posts.isLoading);
  const posts = useSelector((state) => state.posts.data);
  const postFilter = useSelector((state) => state.posts.filter);
  const dispatch = useDispatch();

  useEffect(() => {
    (async () => {
      try {
        const response = await axiosClient.get(
          `post?page=${postFilter.page}&size=${postFilter.limit}`
        );
        dispatch(postActions.setState(response));
      } catch (error) {
        console.log(error);
      }
    })();
    // eslint-disable-next-line
  }, [postFilter.page]);

  const handleChangePage = (page) => {
    const convertedPageValue = +page;
    if (typeof convertedPageValue == 'number') dispatch(postActions.setPage(page));
  };

  const handleRemove = () => {};

  return (
    <PostPageStyled>
      <Stack direction='horizontal' gap={3}>
        <h1>Manage Posts</h1>
        <Button as={Link} to='/admin/posts/add'>
          Add
        </Button>
      </Stack>
      <ActionTable headers={tableHeaders}>
        {!isLoading &&
          posts.map((post) => (
            <tr key={post.id}>
              <td>{post.id}</td>
              <td style={{ maxWidth: '250px' }}>
                <Link to={`/admin/posts/${post.id}`}>{post.postBody}</Link>
              </td>
              <td>{post.likes.length}</td>
              <td style={{ whiteSpace: 'nowrap' }}>{post.member.name}</td>
              <td>{post.createDate}</td>
              <td>
                <Button
                  variant='primary'
                  style={{ marginRight: '12px' }}
                  as={Link}
                  to={`/admin/posts/edit/${post.id}`}
                >
                  Edit
                </Button>
                <Button variant='danger' onClick={() => handleRemove(post.id)}>
                  Remove
                </Button>
              </td>
            </tr>
          ))}
      </ActionTable>
      <CustomePagination
        totalPage={postFilter.totalPage}
        currentPage={postFilter.page}
        onChangePage={handleChangePage}
      />
    </PostPageStyled>
  );
};

export default PostPage;
