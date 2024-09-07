import { ForwardedRef, SyntheticEvent, forwardRef } from "react"
import { useMutation } from "react-query"
import { UPDATE_CART, CartType, DELTE_CART } from "../../graphql/cart"
import { QueryKeys, getClient, graphqlFetcher } from "../../queryClient"
import ItemData from "./itemData"

const CartItem = (
    { id, product: { imageUrl, price, title, createdAt }, amount }: CartType,
    ref: ForwardedRef<HTMLInputElement>
) => {
    const queryClient = getClient()
    const { mutate: updateCart } = useMutation(
        ({ id, amount }: { id: string, amount: number }) =>
            graphqlFetcher<{ updateCart: CartType }>(UPDATE_CART, { id, amount }),
        {
            onMutate: async ({ id, amount }) => {
                await queryClient.cancelQueries(QueryKeys.CART)
                const { cart: prevCart } = queryClient.getQueryData<{ cart: CartType[] }>(QueryKeys.CART) || { cart: [] }

                const targetIndex = prevCart.findIndex(cartItem => cartItem.id == id)
                if (targetIndex === undefined || targetIndex < 0) return prevCart

                const newCart = [...prevCart]
                newCart.splice(targetIndex, 1, { ...prevCart[targetIndex], amount })
                queryClient.setQueryData(QueryKeys.CART, { cart: newCart })
                return prevCart[targetIndex]
            },

            onSuccess: ({ updateCart }) => {
                const { cart: prevCart } = queryClient.getQueryData<{ cart: CartType[] }>(QueryKeys.CART) || { cart: [] }
                const targetIndex = prevCart?.findIndex(cartItem => cartItem.id == updateCart.id)
                if (!prevCart || targetIndex === undefined || targetIndex < 0) return

                const newCart = [...prevCart]
                newCart.splice(targetIndex, 1, updateCart)
                getClient().setQueryData(QueryKeys.CART, { cart: newCart })
            },
        }
    )

    const { mutate: deleteCart } = useMutation(
        ({ id }: { id: string }) => graphqlFetcher(DELTE_CART, { id }),
        {
            onSuccess: () => {
                queryClient.invalidateQueries(QueryKeys.CART)
                // CART useQuery를 쓰는 곳이 10곳이면 invalidateQueries는
                // 한 번의 업데이트로 10곳을 갱신할 수 있다.
            }
        }
    )

    const handleUpdateAmount = (e: SyntheticEvent) => {
        const amount = Number((e.target as HTMLInputElement).value)
        if (amount < 1) return
        updateCart({ id, amount })
    }

    const handleDeleteItem = () => {
        deleteCart({ id })
    }

    return (
        <li className="cart-item">
            <input
                className="cart-item__checkbox"
                type="checkbox"
                name="select-item"
                ref={ref}
                data-id={id}
                disabled={!createdAt}
            />
            <ItemData imageUrl={imageUrl} price={price} title={title} />
            {!createdAt ? (
                <div>삭제된 상품입니다.</div>
            ) : (
                <input
                    className="cart-item__amount"
                    type="number"
                    value={amount}
                    min={1}
                    onChange={handleUpdateAmount}
                />
            )}
            <button
                className="cart-item__button"
                type="button"
                onClick={handleDeleteItem}
            >
                삭제
            </button>
        </li>
    )
}

export default forwardRef(CartItem)
