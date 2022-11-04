import React, { useEffect } from 'react';
import Button from 'react-bootstrap/Button';
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import Stack from 'react-bootstrap/Stack';
import axiosClient from '../api/axiosClient';
import CustomPagination from '../components/common/CustomPagination';
import ActionTable from '../components/tables/ActionTable';
import { userActions } from '../features/users/userSlice';

const AccountPageStyled = styled.div`
  h1 {
    margin-bottom: 12px;
  }
`;

const AccountPage = () => {
  const tableHeaders = ['ID', 'Username', 'Password', 'Name', 'Birthday', 'Email', 'Roles'];
  const users = useSelector((state) => state.users.data);
  const userFilter = useSelector((state) => state.users.filter);
  const dispatch = useDispatch();

  useEffect(() => {
    (async () => {
      try {
        const response = await axiosClient.get(
          `/admin/member?q=&page=${userFilter.page}&size=${userFilter.limit}`
        );
        dispatch(userActions.setState(response));
      } catch (error) {
        console.log(error);
      }
    })();
    // eslint-disable-next-line
  }, [userFilter]);

  const handleChangePage = (page) => {
    const convertedPageValue = +page;
    if (typeof convertedPageValue == 'number') dispatch(userActions.setPage(page));
  };

  const handleRemove = (userId) => {
    alert('Are you sure?');
  };

  return (
    <AccountPageStyled>
      <Stack direction='horizontal' gap={3}>
        <h1>Manage Users</h1>
        <Button as={Link} to='/admin/users/add'>
          Add
        </Button>
      </Stack>
      <ActionTable headers={tableHeaders} onRemove={handleRemove}>
        {users.length > 0 &&
          users.map((user) => (
            <tr key={user.id}>
              <td>{user.id}</td>
              <td>{user.username}</td>
              <td>{user.password || 'Chưa cập nhật'}</td>
              <td>{user.name}</td>
              <td>{user.dob}</td>
              <td>{user.email}</td>
              <td>{user.roles}</td>
              <td>
                <Button
                  variant='primary'
                  style={{ marginRight: '12px' }}
                  as={Link}
                  to={`/admin/users/edit/${user.id}`}
                >
                  Edit
                </Button>
                <Button variant='danger' onClick={() => handleRemove(user.id)}>
                  Remove
                </Button>
              </td>
            </tr>
          ))}
      </ActionTable>
      <CustomPagination
        currentPage={userFilter.page}
        totalPage={userFilter.totalPage}
        onChangePage={handleChangePage}
      />
    </AccountPageStyled>
  );
};

export default AccountPage;
