import { DetailOrderDTO } from "@/hooks/useOrders";

interface Props {
  details: DetailOrderDTO[];
}

const TableDetail: React.FC<Props> = ({ details }) => {
  return (
    <table className="w-full text-sm border mt-2 bg-white">
      <thead>
        <tr className="bg-gray-800 text-white">
          <th className="px-2 py-1 border">Flower</th>
          <th className="px-2 py-1 border">Đơn vị</th>
          <th className="px-2 py-1 border">Số Lượng</th>
        </tr>
      </thead>
      <tbody>
        {details.map((d, i) => (
          <tr className="px-2 py-1 border text-center text-black" key={i}>
            <td>{d.flowerTypeName}</td>
            <td>{d.saleUnitName}</td>
            <td>{d.quantity}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default TableDetail;
