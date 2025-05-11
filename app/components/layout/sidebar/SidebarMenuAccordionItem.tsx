import React from 'react'
import {
  Accordion,
  AccordionContent,
  AccordionItem,
  AccordionTrigger,
} from "@/app/components/ui/accordion"
import SidebarMenuItem from './SidebarMenuItem';

interface SidebarMenuAccordionItemProps{
    title: string;
    icon: React.ElementType;
    children: any[];
}

const SidebarMenuAccordionItem = ({title, icon:Icon, children}:SidebarMenuAccordionItemProps) => {
  return (
    <Accordion type="single" collapsible className='ml-2'>
        <AccordionItem value="item-1">
            <AccordionTrigger>{title}</AccordionTrigger>
            <AccordionContent>
            {children.map((menuItem)=>(
                <SidebarMenuItem key={menuItem.title} title={menuItem.title} icon={menuItem.icon} href={menuItem.href}/>
            ))}
            </AccordionContent>
        </AccordionItem>
    </Accordion>
  )
}

export default SidebarMenuAccordionItem