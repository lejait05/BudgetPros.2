

const mapCreateTransaction =(amount, memo, title, amount, transactionType, budgetCategories) =>
{
    return `<form action="/transactions/create" method="post" object="${transaction}">
            <label for="title">Title</label>
            <input th:field="*{title}" type="text" id="title" name="title" placeholder="Enter Title">
            <hr>
            <label for="amount">Amount</label>
            <input th:field="*{amount}" type="number" id="amount" name="amount" placeholder="$0.00">
            <hr>
            <label for="memo">Transaction Details</label>
            <textarea th:field="*{memo}" name="memo" id="memo" placeholder="Transaction Details"></textarea>
            <hr>
            <label for="transactionType">Transaction Type</label>
            <select th:field="*{transactionType}" name="transactionType" id="transactionType">
                <option value="1">one-time deposit</option>
                <option value="2">one-time expense</option>
                <option value="3">recurring income</option>
                <option value="4">recurring expense</option>
            </select>
            <hr>
            <label for="budgetCategories">Budget Category</label>
            <select th:field="*{budgetCategories}" name="budgetCategories" id="budgetCategories">
                <option value="1">none</option>
                <option value="2">electrical</option>
                <option value="3">mortgage</option>
                <option value="4">gas</option>
                <option value="5">fuel</option>
                <option value="6">water</option>
                <option value="7">loan</option>
                <option value="8">groceries</option>
            </select>
            </form>`
}