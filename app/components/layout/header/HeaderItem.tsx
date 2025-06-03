import Link from "next/link";

interface HeaderItemProps {
    
    icon: React.ElementType;
    href: string
}
export default function HeaderItems({icon:Icon, href}:HeaderItemProps){
    return(
        <Link href={href} className="pr-4 hover:text-blue-400">
            <Icon size={20}/>
        </Link>
    )
}