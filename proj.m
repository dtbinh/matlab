function w = proj(u, v)
%vector projection of u onto v

w = dot(u, v)/norm(v)^2*v;

end
